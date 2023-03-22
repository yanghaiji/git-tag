package com.javayh.gittag;

import org.apache.maven.plugins.annotations.Mojo;

import java.util.List;

/**
 * <p>
 * 生成日志
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-22
 */
@Mojo(name = "cpl")
public class LogTagMojo extends CreateAndPushTagMojo {

    @Override
    protected List<String> getCommand() {
        List<String> command = super.getCommand();
        command.add("git diff");
        return command;
    }
}
