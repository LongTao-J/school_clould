package talks.controller;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import talks.Pojo.ResultData;
import talks.Pojo.STk;
import talks.Pojo.School_talk;
import talks.Server.Del_talk_server;
import talks.Server.School_talk_server;
import talks.Server.Up_talk_Server;
import talks.mapper.Testmapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Classname TestCon
 * @Description TODO
 * @Date 2023/4/5 11:40
 * @Created by wang
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableWebMvc
@RestController
@RequestMapping("/school")
public class Talks {
    @Resource
    private Testmapper tests;
    @Resource
    private School_talk_server schoolTalkServer;
    @Resource
    private Del_talk_server delTalkServer;
    @Resource
    private Up_talk_Server upTalkServer;

    @GetMapping("/lists")
    public List<STk> findalllist(){
        List<STk> list =tests.findalltk();
        return list;
    }
    //用户发帖，在数据库中创建记录
    @PostMapping("/addtalk")
    public ResultData addtalk(@RequestBody School_talk schoolTalk) {
//        School_talk schoolTalk = new School_talk();
//        schoolTalk.setTId(tid);
//        schoolTalk.setTUid(uid);
//        schoolTalk.setTImg(t_img);
//        schoolTalk.setTSource(source);
//        schoolTalk.setTData(t_date);
//        schoolTalk.setTUscCount(0);
//        schoolTalk.setTLikeCount(0);
//        schoolTalk.setTStatus("正常");
//        schoolTalkServer.addtalk(schoolTalk);
        schoolTalkServer.addtalk(schoolTalk);
        ResultData resultData = new ResultData("200","OK!","ok!");
        return resultData;
    }
    //删除帖子按照帖子ID删除
    @GetMapping("/deltalk")
    public ResultData deltalk(@RequestParam("t_id") String tid){
        delTalkServer.deltalk(tid);
        ResultData resultData = new ResultData("200","OK!","增加成功!");
        return resultData;
    }

    @PostMapping("/Uptalk")
    public ResultData uptalk(@RequestBody School_talk schoolTalk){
        upTalkServer.uptalk(schoolTalk);
        ResultData resultData = new ResultData("200","OK!","修改成功!");
        return resultData;
    }
}