package com.javayh.gittag;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * <p>
 * 定义抽象的公共行为与执行动作
 * </p>
 *
 * @author haiji
 */
public abstract class AbstractTagMojo extends AbstractMojo {

    /**
     * maven工程的版本号
     */
    @Parameter(defaultValue = "${project.version}", required = true, readonly = true)
    protected String version;


    protected Log log = getLog();


    @Override
    public void execute() {
        List<String> commandStr = getCommand();
        commandStr.forEach(this::doExecute);
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
    protected void doExecute(String command) {
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(command);
            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String text = command + "\n";
            log.info(String.format("Command: %s", text));
            String line;
            while ((line = input.readLine()) != null) {
                log.info(String.format("Result: %s", line));
            }
        } catch (IOException e) {
            log.error(e);
        }
    }

    public String getVersion() {
        return version;
    }
}
