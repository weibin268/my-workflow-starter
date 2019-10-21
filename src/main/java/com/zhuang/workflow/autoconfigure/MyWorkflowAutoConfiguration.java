package com.zhuang.workflow.autoconfigure;

import com.zhuang.workflow.AbstractWorkflowEngine;
import com.zhuang.workflow.WorkflowActionListener;
import com.zhuang.workflow.WorkflowDeployment;
import com.zhuang.workflow.WorkflowQueryManager;
import com.zhuang.workflow.impl.activiti.ActivitiWorkflowDeployment;
import com.zhuang.workflow.impl.activiti.ActivitiWorkflowEngine;
import com.zhuang.workflow.impl.activiti.ActivitiWorkflowQueryManager;
import com.zhuang.workflow.impl.activiti.handler.CreateUserHandler;
import com.zhuang.workflow.impl.activiti.handler.RoleIdsHandler;
import com.zhuang.workflow.impl.activiti.manager.*;
import org.activiti.spring.boot.DataSourceProcessEngineAutoConfiguration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConditionalOnBean(SqlSessionFactory.class)
@EnableConfigurationProperties(MyWorkflowProperties.class)
@AutoConfigureAfter(DataSourceProcessEngineAutoConfiguration.DataSourceProcessEngineConfiguration.class)
public class MyWorkflowAutoConfiguration {

    @Autowired
    private MyWorkflowProperties myWorkflowProperties;

    @Bean
    public ActivitiWorkflowEngine workflowEngine() {
        ActivitiWorkflowEngine workflowEngine = new ActivitiWorkflowEngine();
        AbstractWorkflowEngine.WorkflowActionListenerMap workflowActionListenerMap = new AbstractWorkflowEngine.WorkflowActionListenerMap();
        workflowEngine.setWorkflowActionListeners(workflowActionListenerMap);
        AbstractWorkflowEngine.NextTaskUsersHandlerMap nextTaskUsersHandlerMap = new AbstractWorkflowEngine.NextTaskUsersHandlerMap();
        nextTaskUsersHandlerMap.put("roleIds", roleIdsHandler());
        nextTaskUsersHandlerMap.put("createUser", createUserHandler());
        workflowEngine.setNextTaskUsersHandlers(nextTaskUsersHandlerMap);
        return workflowEngine;
    }

    @Bean
    RoleIdsHandler roleIdsHandler() {
        return new RoleIdsHandler();
    }

    @Bean
    CreateUserHandler createUserHandler() {
        return new CreateUserHandler();
    }

    @Bean
    public WorkflowQueryManager workflowQueryManager() {
        return new ActivitiWorkflowQueryManager();
    }

    @Bean
    public WorkflowDeployment workflowDeployment() {
        return new ActivitiWorkflowDeployment();
    }

    @Bean
    public DeploymentManager deploymentManager(){
        DeploymentManager deploymentManager=new DeploymentManager();
        deploymentManager.setBasePath("diagrams/");
        return deploymentManager;
    }

    @Bean
    public ProcessDefinitionManager processDefinitionManager(){
        return new ProcessDefinitionManager();
    }

    @Bean
    public ProcessVariablesManager processVariablesManager(){
        return new ProcessVariablesManager();
    }

    @Bean
    public UserTaskManager userTaskManager(){
        return new UserTaskManager();
    }

    @Bean
    public ProcessInstanceManager processInstanceManager(){
        return new ProcessInstanceManager();
    }

}
