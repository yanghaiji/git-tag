package com.javayh.gittag;

import org.apache.maven.plugins.annotations.Mojo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>删除标签</p>
 *
 * @author haiji
 */
@Mojo(name = "delete")
public class DeleteTagMojo extends AbstractTagMojo {

    @Override
    protected List<String> getCommand() {
        List<String> commands = new ArrayList<>();
        commands.add(String.format("git tag -d %s", getVersion()));
        return commands;
    }

}
