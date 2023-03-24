package com.javayh.gittag.maven.plugin.abstracts;

import com.javayh.gittag.maven.plugin.util.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 * 定义抽象的公共行为与执行动作
 * </p>
 *
 * @author haiji
 */
public abstract class AbstractLogTagMojo extends AbstractMojo {

    /**
     * maven工程的版本号
     */
    @Parameter(defaultValue = "${project.version}", required = true, readonly = true)
    protected String version;


    /**
     * 需要对比的当前tag名字
     * 如果不指定取git tag 第一个值
     */
    @Parameter
    protected String beforeTag;


    @Parameter(defaultValue = "${project.basedir}", required = true)
    protected String filePath;

    protected Log log = getLog();

    @Override
    public void execute() {
        Map<String, String> cache = new HashMap<>();
        List<String> commandStr = getCommand();
        AtomicInteger ati = new AtomicInteger(2);
        commandStr.forEach(o -> {
            doExecute(o, ati, cache);
        });
    }

    /**
     * 不同的指令信息
     */
    protected abstract List<String> getCommand();

    /**
     * 具体执行逻辑
     *
     * @param command 脚本命令
     */
    protected void doExecute(String command, AtomicInteger ati, Map<String, String> cache) {
        try {
            Runtime runtime = Runtime.getRuntime();
            if (command.contains("log") && StringUtils.isEmpty(beforeTag)) {
                command = "git log --pretty=format:\"%h - %an, %ar : %s\" --left-right " + cache.get("beforeTag") + "..." + cache.get("afterTag");
            }
            Process process = runtime.exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String text = command + "\n";
            log.info(String.format("Command: %s", text));
            String line;
            if (command.contains("log")) {
                FileUtils.writer(input, getFilePath(), version);
            } else if (command.equals("git tag")) {
                while ((line = input.readLine()) != null) {
                    if (ati.get() == 2) {
                        cache.put("beforeTag", line);
                    }
                    if (ati.get() == 1) {
                        cache.put("afterTag", line);
                    }
                    ati.decrementAndGet();
                }
            }
        } catch (
                IOException e) {
            log.error(e);
        }

    }


    public String getVersion() {
        return version;
    }


    public String getBeforeTag() {
        return beforeTag;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setBeforeTag(String beforeTag) {
        this.beforeTag = beforeTag;
    }
}
