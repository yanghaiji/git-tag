package com.javayh.gittag.maven.plugin.log;

import com.javayh.gittag.maven.plugin.abstracts.AbstractTagMojo;
import org.apache.maven.plugins.annotations.Mojo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 生成日志
 *
 * </p>
 *
 * @author hai ji
 * @version 1.0.0
 * @since 2023-03-22
 */
@Mojo(name = "contrast")
public class LogTagMojo extends AbstractTagMojo {

    /**
     * <p>
     * 只查看版本之间差异，显示出每个差异commit所在的版本。
     * 注意 commit 后面的箭头，根据我们在 –left-right v1.0…v2.0 的顺序，左箭头 < 表示是 v1.0 的，右箭头 > 表示是 v2.0的。
     * </p>
     *
     * @version 1.0.0
     * @author hai ji
     * @since 2023/3/23
     */
    @Override
    protected List<String> getCommand() {
        String afterTag = getAfterTag();
        String beforeTag = getBeforeTag();
        List<String> command = new ArrayList<>();
        command.add("git tag");
        command.add("git log --pretty=format:\"%h - %an, %ar : %s\" --left-right " + String.format("%s...%s", beforeTag, afterTag));
        return command;
    }
}
