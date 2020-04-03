package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 定义请求模型QueryPageRequest，
 * 此模型作为查询条件类型为后期扩展需求，请求类型统一继承RequestData类型。
 */
@Data
public class QueryPageRequest extends RequestData {
    // 站点id
    @ApiModelProperty("站点id")
    private String siteId;
    // 页面id
    @ApiModelProperty("页面ID")
    private String pagaid;
    // 页面名称
    @ApiModelProperty("页面名称")
    private String pageName;
    // 别名
    @ApiModelProperty("别名")
    private String pageAliase;
    // 模板
    @ApiModelProperty("模板")
    private String templateId;
}
