package edu.ustb.sei.mde.nmc.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.eclipse.jgit.api.*;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.transport.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.*;

public class PorcessTest {

    public static final String baseUrl = "http://101.43.149.150:8080";
    public static final String apiUrl = baseUrl + "/gitserver/";

    public static void main(String[] args) throws IOException, GitAPIException, URISyntaxException, ParseException {

//        signUpTest("alice", "alice", "alice@example.com", "alice1234");

        // 管理员alice创建远程项目，同时初始化本地库，并创建本地分支branch-alice。
//        String projectName = "newp";
//        String localPath = "C:\\Users\\10242\\Desktop\\newp";
//        createProject("alice", "alice1234", projectName, localPath);

        // 管理员alice查看远程项目的信息。
//        getProjects("alice", "alice1234");

        // 切换到本地仓库的master分支，准备要push的文件
//        String localPath = "C:\\Users\\10242\\Desktop\\newp";
//        checkOut(localPath, "master");

        // 管理员alice推送到远程项目的master分支。
//        String localPath = "C:\\Users\\10242\\Desktop\\newp";
//        String message = "first commit";
//        String remotePath = "http://101.43.149.150/alice/newp.git";
//        pushToRemote("alice", "alice1234", localPath, "master", message, remotePath, false);

        // 管理员alice切换到本地仓库的branch-alice分支，准备要push的文件
//        String localPath = "C:\\Users\\10242\\Desktop\\newp";
//        checkOut(localPath, "branch-alice");

        // 管理员alice推送到远程项目的branch-alice分支。
//        String localPath = "C:\\Users\\10242\\Desktop\\newp";
//        String message = "modify 1.0";
//        String remotePath = "http://101.43.149.150/alice/newp.git";
//        pushToRemote("alice", "alice1234", localPath, "branch-alice", message, remotePath, false);

        // 管理员alice查看远程项目的信息, 记录uri和pid。
//        getProjects("alice", "alice1234");

        // 管理员可以根据远程项目的pid添加项目成员。
//        String newMemberName = "lyt";
//        String projectId = "30";
//        String accessLevel = "30";  // 开发者级别
//        addMemmber("alice", "alice1234", newMemberName, projectId, accessLevel);

        // 管理员可以根据远程项目的pid添加项目成员。
//        String newMemberName = "lesley";
//        String projectId = "30";
//        String accessLevel = "30";  // 开发者级别
//        addMemmber("alice", "alice1234", newMemberName, projectId, accessLevel);

        





        // 当成员lyt被添加进项目后，可以获取所参与的远程项目的信息。
//        getProjects("lyt", "lyt12345");

        // 根据远程项目的uri，成员lyt可以clone远程项目，同时创建和切换至本地分支branch-lyt。
//        String remotePath = "http://101.43.149.150/alice/newp.git";
//        String localPath = "C:\\Users\\10242\\Desktop\\newpp";
//        cloneRepo("lyt", "lyt12345", remotePath, localPath);

        // 成员lyt只能push到远程项目的branch-lyt分支。
        // 就在本地的branch-lyt分支，不用切换，准备好要push的文件
//        String localPath = "C:\\Users\\10242\\Desktop\\newpp";
//        String message = "modify #1.0";
//        String remotePath = "http://101.43.149.150/alice/newp.git";
//        pushToRemote("lyt", "lyt12345", localPath, "branch-lyt", message, remotePath, false);

        // 根据远程项目的uri，成员lesley可以clone远程项目，同时创建和切换至本地分支branch-lesley。
//      String remotePath = "http://101.43.149.150/alice/newp.git";
//      String localPath = "C:\\Users\\10242\\Desktop\\newppp";
//      cloneRepo("lesley", "lesley1234", remotePath, localPath);

      // 成员lesley只能push到远程项目的branch-lesley分支。
      // 就在本地的branch-lesley分支，不用切换，准备好要push的文件
//      String localPath = "C:\\Users\\10242\\Desktop\\newppp";
//      String message = "modify #1.0";
//      String remotePath = "http://101.43.149.150/alice/newp.git";
//      pushToRemote("lesley", "lesley1234", localPath, "branch-lesley", message, remotePath, false);


        
        
        
        
        


        // 成员lyt插入修改记录到黑板
//        String projectId = "30";
//        JSONObject params = new JSONObject();
//        params.put("id", "55");
//        params.put("type", "delete");
//        insert("lyt", "lyt12345", projectId, params.toString());

        // 管理员alice插入修改记录到黑板
//        String projectId = "30";
//        JSONObject params = new JSONObject();
//        params.put("id", "33");
//        params.put("type", "add");
//        params.put("content", "22");
//        insert("alice", "alice1234", projectId, params.toString());


        // 成员lyt查看全部黑板记录
//        String projectId = "30";
//        findAll("lyt", "lyt12345", projectId);

//        // 成员lyt查看某时间后的黑板记录
//        String projectId = "30";
//        String date = "2022-02-25 15:00:00";
//        find("lyt", "lyt12345", projectId, date);

        // 黑板的实时方式
//        String projectId = "30";
//        realTime("lyt", "lyt12345", projectId);
//        realTime("alice", "alice1234", projectId);
















        // 管理员alice查看远程项目的信息。
//        getProjects("alice", "alice1234");

        // 虽然成员都可以下载全部分支的文件，都能在本地进行多向合并，但只有管理员能push到远程项目的master分支。
        // 管理员alice下载全部分支文件。
//        String projectId = "30";
//        String downloadPath = "C:\\Users\\10242\\Desktop\\files.zip";
//        download("alice", "alice1234", projectId, downloadPath);

        // 管理员alice切换到本地master分支，进行本地的多向合并后
//        String localPath = "C:\\Users\\10242\\Desktop\\newp";
//        checkOut(localPath, "master");
    	
    	
    	
    	
    	// 调用多向合并方法
//    	Unzipper.unzip();
        
        // PENDING: 根据master目录下的模型文件名，找到其它目录下对应的模型文件，进行多向合并。
    	
    	
    	

        // 再push到远程项目的master分支。
        // 同时，本地master分支覆盖本地branch-alice分支，再push到远程项目的branch-alice分支。
//        String localPath = "C:\\Users\\10242\\Desktop\\newp";
//        String message = "master#1.0";
//        String remotePath = "http://101.43.149.150/alice/newp.git";
//        pushToRemote("alice", "alice1234", localPath, "master", message, remotePath, false);
//        cover(localPath, "branch-alice");
//        pushToRemote("alice", "alice1234", localPath, "branch-alice", message, remotePath, true);








    	
    	





        // 成员lyt查看远程项目的master分支是否有更新
        String projectId = "30";
        String localPath = "C:\\Users\\10242\\Desktop\\newpp";
        checkOut(localPath, "master");
        checkUpdate("lyt", "lyt12345", projectId, localPath);
        
        
        // 成员lyt需要频繁地pull远程项目的master分支，同步本地的master分支。
//        String localPath = "C:\\Users\\10242\\Desktop\\newpp";
//        checkOut(localPath, "master");
//        pullRepo("lyt", "lyt12345", localPath);


        // 成员lyt可以选择本地master分支覆盖本地branch-lyt分支，
        // 或者将其与自己的分支进行二向合并，再push到远程项目的branch-lyt分支。
//        String localPath = "C:\\Users\\10242\\Desktop\\newpp";
//        String message = "covered by master#1.0";
//        String remotePath = "http://101.43.149.150/alice/newp.git";
//        cover(localPath, "branch-lyt");
//        pushToRemote("lyt", "lyt12345", localPath, "branch-lyt", message, remotePath, true);


    }
    
    public static void checkUpdate(String username, String password, String projectId, String localPath) throws IOException {
        String token = signInTest(username, password);

        Path path = Paths.get(localPath);
        Git git = Git.open(path.toFile());
        Repository repository = git.getRepository();

        ObjectId lastCommitId = repository.resolve(Constants.HEAD);
        RevWalk revWalk = new RevWalk(repository);
        RevCommit commit = revWalk.parseCommit(lastCommitId);
        int time = commit.getCommitTime();
        System.out.println("last CommitTime: " + time);

        String res = gitGet("project/checkUpdate"+ "?time=" + time + "&id=" + projectId + "&token=" + token,200);
        System.out.println("\n\n" + res);
    }

    public static void realTime(String username, String password, String projectId) throws IOException, URISyntaxException {
        String token = signInTest(username, password);
        WebSocket c = new WebSocket(new URI(
                "ws://101.43.149.150:8080/websocket/" + token + "/" + projectId));
        c.connect();
    }

    public static void find(String username, String password, String projectId, String date) throws IOException {
        String token = signInTest(username, password);
        // date中的空格要转成%20！！！
        date = date.replaceAll(" ", "%20");
        String res = gitGet("blackboard/find"+"?token="+token+"&date="+date+"&projectId=" + projectId,200);
        System.out.println("\n\n" + res);
    }

    public static void findAll(String username, String password, String projectId) throws IOException {
        String token = signInTest(username, password);
        String res = gitGet("blackboard/findAll"+"?token="+token+"&projectId=" + projectId,200);
        System.out.println("\n\n" + res);
    }

    public static void insert(String username, String password, String projectId, String message) throws IOException {
        String token = signInTest(username, password);
        String res =gitPost("blackboard/insert", new HashMap<String, String>() {{
            put("token", token);
            put("projectId", projectId);
            put("message", message);
        }},200);
        System.out.println("\n\n" + res);

        System.out.println("\n\n" + "insert is done");
    }

    public static void pullRepo(String username, String password, String localPath) throws IOException, GitAPIException {
        Path path = Paths.get(localPath);
        Git git = Git.open(path.toFile());

        PullCommand pullCommand = git.pull();
        pullCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
        PullResult result = pullCommand.call();
        FetchResult fetchResult = result.getFetchResult();
//        for (TrackingRefUpdate update : fetchResult.getTrackingRefUpdates()) {
//            System.out.println("\n\nlocalName: " + update.getLocalName());
//            System.out.println("remoteName: " + update.getRemoteName());
//            System.out.println("result: " + update.getResult());
//        }
        System.out.println("pull is done");

    }

    public static void cover(String localPath, String toBranchName) throws IOException, GitAPIException {
        Path path = Paths.get(localPath);
        Git git = Git.open(path.toFile());

        // checkout to specific branch
        System.out.println("before checkout: " + git.getRepository().getFullBranch());
        git.checkout().setName(toBranchName).call();
        System.out.println("after checkout: " + git.getRepository().getFullBranch());

        git.reset().setMode(ResetCommand.ResetType.HARD).setRef("refs/heads/master").call();
        System.out.println("\n\ncover is done");

    }

    public static void download(String username, String password, String projectId, String downloadPath) throws IOException {
        String token = signInTest(username, password);
        MultipartFile allArchive = getAllArchive(projectId, token);
        File file = new File(downloadPath);
        allArchive.transferTo(file);
        System.out.println("\n\ndownload");
    }

    public static MultipartFile getAllArchive(String projectId, String token) throws IOException {
        MultipartFile file = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(apiUrl + "project/getAllArchive"+"?id="+projectId+"&token="+token);
        httpGet.addHeader("PRIVATE-TOKEN", token);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                String filename = response.getFirstHeader("Content-Disposition").toString().split("\"")[1];
                HttpEntity httpEntity = response.getEntity();
                file = new MockMultipartFile(filename, httpEntity.getContent());
            }
            //关闭服务器响应
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public static void cloneRepo( String username, String password, String remotePath, String localPath) throws GitAPIException, IOException {
        Path path = Paths.get(localPath);
        CloneCommand cloneCommand = Git.cloneRepository();
        cloneCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
        Git git = cloneCommand.setURI(remotePath)
                .setDirectory(path.toFile()).call();
        System.out.println("\n\nclone done");

        // 创建本地分支branch-username
        String branchName = "branch-" + username;
        git.branchCreate().setName(branchName).call();
        System.out.println("\n\n" + branchName + " is created");

        // 切换到新创建的分支
        System.out.println("before branch checkout: " + git.getRepository().getFullBranch());
        git.checkout().setName(branchName).call();
        System.out.println("after branch checkout: " + git.getRepository().getFullBranch());
    }

    public static void addMemmber(String username, String password, final String newMemberName, final String projectId, final String accessLevel) throws IOException {

        final String token = signInTest(username, password);
        // {{url}}/project/addMember?projectId=2&username=root&accessLevel=20&token={{token}}
        String res =gitPost("project/addMember", new HashMap<String, String>() {{
            put("projectId", projectId);
            put("username", newMemberName);
            put("accessLevel", accessLevel);
            put("token", token);
        }},200);
        System.out.println("\n\n" + res);
    }

    public static void getProjects(String username, String password) throws IOException {
        String token = signInTest(username, password);
        String res = gitGet("project/getProjects"+"?token="+token,200);
        JSONObject resultJson = JSONObject.parseObject(res);
        Result result = JSON.toJavaObject(resultJson, Result.class);
        System.out.println("\n\nResult Object: " + result);
    }

    public static void checkOut(String localPath, String branchName) throws IOException, GitAPIException {
        Path path = Paths.get(localPath);
        Git git = Git.open(path.toFile());

        // checkout to specific branch
        System.out.println("before checkout: " + git.getRepository().getFullBranch());
        git.checkout().setName(branchName).call();
        System.out.println("after checkout: " + git.getRepository().getFullBranch());
    }

    public static void pushToRemote(String username, String password, String localPath, String branchName, String message, String remotePath, Boolean force) throws IOException, GitAPIException, URISyntaxException {

        Path path = Paths.get(localPath);
        Git git = Git.open(path.toFile());

        // commit
        git.add().addFilepattern(".").call();
        git.commit().setMessage(message).call();
        System.out.println("\n\ncommit done");

        // push
        RemoteAddCommand remoteAddCommand = git.remoteAdd();
        remoteAddCommand.setName(branchName); // the name of remote branch
        remoteAddCommand.setUri(new URIish(remotePath));
        remoteAddCommand.call();

        PushCommand pushCommand = git.push();
        pushCommand.setForce(force);
        pushCommand.setRemote(branchName);    // the name of remote branch
        pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
        Iterable<PushResult> results = pushCommand.call();
        PushResult next = results.iterator().next();
        Collection<RemoteRefUpdate> remoteUpdates = next.getRemoteUpdates();
        if(remoteUpdates.isEmpty()){
            System.out.println("\n\npush done");
        } else{
            for(RemoteRefUpdate r : remoteUpdates){
                System.out.println(r);
            }
        }

    }

    // 当管理员alice创建远程项目时，同时在本地初始化了本地库，
    // 并创建本地分支branch-alice。
    public static void createProject(String username, String password, String projectName,String localPath) throws IOException, GitAPIException, URISyntaxException {

        String token = signInTest(username, password);

        // create remote project
        //完整url为http://localhost:8080/gitserver/project/create?name=test4&token={{token}}
        String res =gitPost("project/create", new HashMap<String, String>() {{
            put("name", projectName);
            put("token", token);
        }},200);
        System.out.println("\n\n" + res);

        // initialize local repository
        init(username, password, projectName, localPath);
    }

    public static void init( String username, String password, String projectName, String localPath) throws GitAPIException, IOException, URISyntaxException {
        Path path = Paths.get(localPath);
        // initialize local repository
        InitCommand init = Git.init();
        init.setDirectory(path.toFile());
        Git git = init.call();
        Repository repository = git.getRepository();
        System.out.println("\n\ninitialize local repository: " + repository.getDirectory());

        // push README.md to remote master
        // or ref head not found error
        Files.writeString(path.resolve("README.md"), "Hello :)");
        git.add().addFilepattern("README.md").call();
        git.commit().setMessage("create README.md").call();
        System.out.println("\n\ncommit done");

        String remotePath = "http://101.43.149.150/" + username + "/" + projectName + ".git";
        RemoteAddCommand remoteAddCommand = git.remoteAdd();
        remoteAddCommand.setName("master"); // the name of remote branch
        remoteAddCommand.setUri(new URIish(remotePath));
        remoteAddCommand.call();

        PushCommand pushCommand = git.push();
        pushCommand.setRemote("master");    // the name of remote branch
        pushCommand.setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
        Iterable<PushResult> results = pushCommand.call();
        PushResult next = results.iterator().next();
        Collection<RemoteRefUpdate> remoteUpdates = next.getRemoteUpdates();
        if(remoteUpdates.isEmpty()){
            System.out.println("\n\npush done");
        } else{
            for(RemoteRefUpdate r : remoteUpdates){
                System.out.println(r);
            }
        }

        // 创建本地分支branch-username
        String branchName = "branch-" + username;
        git.branchCreate().setName(branchName).call();
        System.out.println("\n\n" + branchName + " is created");

        // 切换到新创建的分支
        System.out.println("before checkout: " + git.getRepository().getFullBranch());
        git.checkout().setName(branchName).call();
        System.out.println("after checkout: " + git.getRepository().getFullBranch());
    }

    public static String signInTest(String username, String password) throws IOException {
        //完整url为http://101.43.149.150:8080/gitserver/user/signIn?login=test&password=test1234
        String res1 = gitGet("user/signIn" + "?login=" + username + "&password=" + password, 200);
//        System.out.println("\n\n" + res1);

        JSONObject resultJson = JSONObject.parseObject(res1);
        Result result1 = JSON.toJavaObject(resultJson, Result.class);
        System.out.println("\n\nResult Object: " + result1);
        String token = result1.getData();
        
        return token;
    }

    public static void signUpTest(final String name, final String username, final String email, final String password){
        //完整url为http://101.43.149.150:8080/gitserver/user/signUp?name=test5&username=test5&email=test5@test.com&password=asdf1234
        String res =gitPost("user/signUp", new HashMap<String, String>() {{
            put("name",name);
            put("username", username);
            put("email", email);
            put("password",password);
        }},200);
        System.out.println("\n\n" + res);
    }

    public static String gitGet(String url, int OkCode) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(apiUrl + url);
        return getResponse(httpGet, client, OkCode);
    }

    public static String gitPost(String url, Map<String, String> params, int OkCode) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(apiUrl + url);
        List<NameValuePair> formList = new ArrayList<>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            formList.add(new BasicNameValuePair(key, params.get(key)));
        }
        StringEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(formList, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpPost.setEntity(entity);

        return getResponse(httpPost, client, OkCode);
    }

    public static String getResponse(HttpRequestBase httpRequest, CloseableHttpClient httpClient, int OkCode) {
        CloseableHttpResponse response;
        String res = null;

        try {
            response = httpClient.execute(httpRequest);
            //System.out.println(response.getStatusLine().toString());
            //System.out.println(response.getEntity().getContentType());
            //System.out.println(EntityUtils.toString(response.getEntity()));
            if (response.getStatusLine().getStatusCode() == OkCode) {
                //特别注意EntityUtils.toString会将CloseableHttpResponse的资源消耗掉
                //调用一次后会关闭其中的IOStream，后不能再接任何赋值
                //例如之前的println语句中调用了该函数，解注释后再运行这里会报错Stream closed
                res = EntityUtils.toString(response.getEntity());
            } else {
                res = response.getStatusLine().toString();
            }
            //关闭服务器响应
            response.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
