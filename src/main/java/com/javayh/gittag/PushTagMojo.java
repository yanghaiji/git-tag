package com.javayh.gittag;

import org.apache.maven.plugins.annotations.Mojo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>推送标签</p>
 *
 * @author haiji
 */
@Mojo(name = "push")
public class PushTagMojo extends AbstractTagMojo {


    @Override
    protected List<String> getCommand() {
        List<String> commands = new ArrayList<>();
        String version = getVersion();
        commands.add(String.format("git push origin %s", version));
        return commands;
    }
}
