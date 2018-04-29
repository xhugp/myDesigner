package com.xhu.entity;

/**
 * Created by gp on 2018/3/21.
 */
public class Tool {
    private Integer toolId;
    private String toolName;
    private Integer toolType;
    private String toolDes;
    private String toolImg;
    private String toolState;
    private Integer companyId;

    private String typeName;
    private String stateName;

    public Tool() {
    }

    public Tool(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public Integer getToolType() {
        return toolType;
    }

    public void setToolType(Integer toolType) {
        this.toolType = toolType;
    }

    public String getToolDes() {
        return toolDes;
    }

    public void setToolDes(String toolDes) {
        this.toolDes = toolDes;
    }

    public String getToolImg() {
        return toolImg;
    }

    public void setToolImg(String toolImg) {
        this.toolImg = toolImg;
    }

    public String getToolState() {
        return toolState;
    }

    public void setToolState(String toolState) {
        this.toolState = toolState;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "toolId=" + toolId +
                ", toolName='" + toolName + '\'' +
                ", toolType=" + toolType +
                ", toolDes='" + toolDes + '\'' +
                ", toolImg='" + toolImg + '\'' +
                ", toolState='" + toolState + '\'' +
                ", companyId=" + companyId +
                ", typeName='" + typeName + '\'' +
                ", stateName='" + stateName + '\'' +
                '}';
    }
}
