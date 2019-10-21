package com.zhuang.workflow.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "my.workflow")
public class MyWorkflowProperties {

    private List<String> dbExecutionHandlers;

    public Boolean getUnderscoreNaming() {
        return underscoreNaming;
    }

    public void setUnderscoreNaming(Boolean underscoreNaming) {
        this.underscoreNaming = underscoreNaming;
    }

    private Boolean underscoreNaming;

    public List<String> getDbExecutionHandlers() {
        return dbExecutionHandlers;
    }

    public void setDbExecutionHandlers(List<String> dbExecutionHandlers) {
        this.dbExecutionHandlers = dbExecutionHandlers;
    }
}
