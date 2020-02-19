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
public class HCPRTETranslator {

    /**
     * The Constant log.
     */
    private static final Logger log = LoggerFactory.getLogger(HCPRTETranslator.class);

    /**
     * The resource.
     */
    @SlingObject
    private Resource resource;

    @ValueMapValue
    private String broadcastTitle;

    @ValueMapValue
    private String isiDescription;

    @ValueMapValue
    private String indicationDesc;

    @PostConstruct
    protected void init() {
        log.info("resource :" + resource.getPath());
        if (StringUtils.isNotEmpty(broadcastTitle)) {
            if (broadcastTitle.indexOf("<p>") > -1 || broadcastTitle.indexOf("</p>") > -1) {
                broadcastTitle = broadcastTitle.replaceAll("<p>" ,
                    "<td style=\"padding-top:10px;padding-bottom:0; padding-left:0px; padding-right:0px; font-family:Arial, Helvetica, sans-serif;font-size:24px; font-weight:600; color:#41748d;\">")
                    .replaceAll("</p>" , "</td>");
            }
        }
        if (StringUtils.isNotEmpty(isiDescription)) {
            isiDescription = updateDescription(isiDescription);
        }
        if (StringUtils.isNotEmpty(indicationDesc)) {
            indicationDesc = updateDescription(indicationDesc);
        }
    }

    private String updateDescription(String description) {
        if (description.indexOf("<p>") > -1) {
            description = description.replaceAll("<p>" ,
                "<tr style=\"width:100%; text-align:left;\"><td style=\"padding-top:5px;padding-bottom:10px; padding-right:22px;padding-left:22px;font-family:Arial, Helvetica, sans-serif;color:#484848; font-size:16px; line-height: 1.4; font-weight:400\">");
        }
        if (description.indexOf("<p style=\"font-weight: 600;\">") > -1) {
            description = description.replaceAll("<p style=\"font-weight: 600;\">" ,
                "<tr style=\"width:100%; text-align:left;\"><td style=\"padding-top:5px;padding-bottom:5px; padding-right:22px;padding-left:22px;font-family:Arial, Helvetica, sans-serif;color:#484848; font-size:16px; line-height: 1.4;font-weight:600\">");
        }
        if (description.indexOf("<p style=\"font-style: italic;\">") > -1) {
            description = description.replaceAll("<p style=\"font-style: italic;\">" ,
                "<tr style=\"width:100%; text-align:left;\"><td style=\"padding-top:5px;padding-bottom:5px; padding-right:22px;padding-left:22px;font-family:Arial, Helvetica, sans-serif;color:#484848; font-size:16px; font-weight:400; font-style:italic;\">");
        }
        if (description.indexOf("</p>") > -1) {
            description = description.replaceAll("</p>" , "</td></tr>");
        }
        return description;
    }

    public String getBroadcastTitle() {
        return broadcastTitle;
    }

    public String getIsiDescription() {
        return isiDescription;
    }

    public String getIndicationDesc() { return indicationDesc; }

}
