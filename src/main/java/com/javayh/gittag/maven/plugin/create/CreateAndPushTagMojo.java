package com.javayh.gittag.maven.plugin.create;

import com.javayh.gittag.maven.plugin.abstracts.AbstractTagMojo;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 创建并推送
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-22
 */
@Mojo(name = "cp")
public class CreateAndPushTagMojo extends AbstractTagMojo {

    @Override
    protected List<String> getCommand() {
        List<String> commands = new ArrayList<>();
        String version = getVersion();
        String msg = "在" + DateFormatUtils.format(new Date(), DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern()) +
                "进行了版本发布,对应的version/tag为:" + version;
        commands.add(String.format("git tag %s -m  %s", version, msg));
        commands.add(String.format("git push origin %s", version));
        return commands;
    }
}