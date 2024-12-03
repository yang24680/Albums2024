package com.folder.conreoller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.api.model.v2.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.folder.service.folderService;
import com.ttt.model.file_folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Map;

@RestController
public class folderController {
    @Autowired
    folderService FS;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("init")
    @SentinelResource(value = "initSentinelResource" ,blockHandler = "initBlockHandler")
    public Integer init(){
        return FS.init();
    }
    public String initBlockHandler(BlockException e){
        return "init 服务不可用";
    }



    @RequestMapping("/createFolder")
    public Result create_folder(@RequestBody Map<String, Collection<file_folder>> mp)
    {
        System.out.println(mp);
        file_folder folder =  mp.get("folderList").iterator().next();

        file_folder res =  FS.init(folder);
        file_folder pre_f = FS.getOne(res.getPreDirId());
        FS.silver_bullet_I(pre_f, res);

        return Result.success(res);
    }

    @RequestMapping("/updateFolder")
    public Result updateFolderName(@RequestBody Map<String,Collection<file_folder>> mp)
    {
        Collection<file_folder> data = mp.get("folderList");
        data.forEach( (v) -> {
            FS.updateFolder(v);
            file_folder pre_f =  FS.getOne( v.getPreDirId() );
            file_folder file_back = FS.getOne( v.getId() );
            file_back.setDirName( v.getDirName() );
            FS.silver_bullet_D(pre_f, v.getId(), 1);
            FS.silver_bullet_I(pre_f, file_back);
        });

        return Result.success();
    }

    @RequestMapping("deleteFolder")
    public  Result deleteFolder(@RequestBody  Map<String, Collection<file_folder>> meta)
    {
        Collection<file_folder> data = meta.get("folderList");
        data.forEach(e -> {
            Integer id = e.getId();
            FS.deleteFolder(id); // 应还要链式删除底下的子节点（包括图片，文件夹）
            file_folder pre_f =  FS.getOne( e.getPreDirId() );
            FS.silver_bullet_D(pre_f, id, 1);
        });

        return Result.success();
    }

}
