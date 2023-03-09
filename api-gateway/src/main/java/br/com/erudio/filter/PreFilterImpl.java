package br.com.erudio.filter;



import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;



@Component
public class PreFilterImpl extends ZuulFilter {

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        // Implemente suas verificações aqui
        // Exemplo: verificar se o usuário tem as permissões necessárias
        if (!usuarioTemPermissao(request)) {
            ctx.setSendZuulResponse(false);
            ctx.setResponseBody("Usuário não tem permissão para acessar o serviço");
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            return null;
        }

        return null;
    }

    private boolean usuarioTemPermissao(HttpServletRequest request) {
        // Implemente aqui a verificação de permissões do usuário
        return true;
    }
}
