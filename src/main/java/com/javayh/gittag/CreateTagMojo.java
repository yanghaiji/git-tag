package com.javayh.gittag;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>创建标签</p>
 *
 * @author haiji
 */
@Mojo(name = "create")
public class CreateTagMojo extends AbstractTagMojo {

    @Override
    protected List<String> getCommand() {
        List<String> commands = new ArrayList<>();
        String version = getVersion();
        String msg = "在" + DateFormatUtils.format(new Date(), DateFormatUtils.ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT.getPattern()) +
                "进行了版本发布,对应的version/tag为:" + version;
        commands.add(String.format("git tag %s -m  %s", version, msg));
        return commands;
    }
}
