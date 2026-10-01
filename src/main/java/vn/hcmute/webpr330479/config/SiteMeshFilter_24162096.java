package vn.hcmute.webpr330479.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.webapp.DispatchMode;

public class SiteMeshFilter_24162096 extends ConfigurableSiteMeshFilter {

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.setDecoratorPrefix("/")
               .setDispatchMode(DispatchMode.INCLUDE)
               .addExcludedPath("/decorators/*")
               .addExcludedPath("/WEB-INF/*")
               .addExcludedPath("/static/*")
               .addDecoratorPath("/admin/*", "decorators/admin.jsp")
               .addDecoratorPath("/*", "decorators/user.jsp")
               .addDecoratorPath("/**", "decorators/user.jsp");
    }
}
