package com.lundbeck.campaign.models;

import javax.annotation.PostConstruct;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This model is used to replace p tags added in RTE with respective styles
 *
 * @Author +Vandana
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PatientRTETranslator {

    /**
     * The Constant log.
     */
    private static final Logger log = LoggerFactory.getLogger(PatientRTETranslator.class);

    /**
     * The resource.
     */
    @SlingObject
    private Resource resource;

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String approvedDesc;

    @ValueMapValue
    private String isiDescription;

    @ValueMapValue
    private String threeMonthsDesc;

    @ValueMapValue
    private String stayTunedDesc;

    @PostConstruct
    protected void init() {
        log.info("resource :" + resource.getPath());
        if (StringUtils.isNotEmpty(heading)) {
            if (heading.indexOf("<p>") > -1 || heading.indexOf("</p>") > -1) {
                heading = heading.replaceAll("<p>" ,
                    " <td style=\"padding-top:30px;padding-bottom:20px; padding-right:80px;padding-left:22px;font-family:Arial, Helvetica, sans-serif;color:#41748D; font-size:30px; font-weight:600;\">")
                    .replaceAll("</p>" , "</td>");
            }
        }
        if (StringUtils.isNotEmpty(threeMonthsDesc)) {
            if (threeMonthsDesc.indexOf("<p>") > -1 || threeMonthsDesc.indexOf("</p>") > -1) {
                threeMonthsDesc = threeMonthsDesc.replaceAll("<p>" ,
                    "<td style=\"padding-top:20px;padding-bottom:20px;padding-left:5px;padding-right:22px; text-align:left; font-family:Arial, Helvetica, sans-serif; color:#484848; font-size:22px; line-height: 1.3; font-weight:700\">")
                    .replaceAll("</p>" , "</td>");
            }
        }
        if (StringUtils.isNotEmpty(stayTunedDesc)) {
            if (stayTunedDesc.indexOf("<p>") > -1 || stayTunedDesc.indexOf("</p>") > -1) {
                stayTunedDesc = stayTunedDesc.replaceAll("<p>" ,
                    "<td style=\"padding-top:10px;padding-bottom:10px; padding-left:22px; padding-right:22px; font-family:Arial, Helvetica, sans-serif;background-color:#41748D; font-size:16px; font-weight:600; color:#fff; text-align:left;\">")
                    .replaceAll("</p>" , "</td>");
            }
        }
        if (StringUtils.isNotEmpty(approvedDesc)) {
            approvedDesc = updateDescription(approvedDesc);
        }
        if (StringUtils.isNotEmpty(isiDescription)) {
            isiDescription = updateDescription(isiDescription);
        }
    }

    private String updateDescription(String description) {
        if (description.indexOf("<p>") > -1 || description.indexOf("</p>") > -1) {
            description = description.replaceAll("<p>" ,
                "<tr style=\"width:100%; text-align:left;\"><td style=\"padding-top:5px;padding-bottom:10px; padding-right:22px;padding-left:22px;font-family:Arial, Helvetica, sans-serif;color:#484848; font-size:16px; line-height: 1.4; font-weight:400\">")
                .replaceAll("</p>" , "</td></tr>");
        }
        return description;
    }

    public String getApprovedDesc() {
        return approvedDesc;
    }

    public String getIsiDescription() {
        return isiDescription;
    }

    public String getHeading() {
        return heading;
    }

    public String getThreeMonthsDesc() {
        return threeMonthsDesc;
    }

    public String getStayTunedDesc() {
        return stayTunedDesc;
    }

}
