#!/usr/bin/lua
module = {}
module.name = "print_ngx_value"

local function contactHtmlStringForPTag(htmlStr)
        html_prefix = "<p>"
        html_suffix = "</p>"
        return html_prefix..htmlStr..html_suffix
end

local function contactHtmlStringForH2Tag(htmlStr)
        html_prefix = "<h2>"
        html_suffix = "</h2>"
        return html_prefix..htmlStr..html_suffix
end

function module.myPrint(input)
        local str = contactHtmlStringForPTag(input)
        ngx.say(str)
end

function module.myTitlePrint(input)
        local str = contactHtmlStringForH2Tag(input)
        ngx.say(str)
end

function module.print_ngx_value()
        module.myTitlePrint("start to print nginx value...")
        module.myPrint("$uri ==="..ngx.var.uri)
        module.myPrint("$request_uri === "..ngx.var.request_uri)
        module.myPrint("$scheme === "..ngx.var.scheme)
        module.myPrint("$http_host === "..ngx.var.http_host);
        module.myPrint("$host === "..ngx.var.host);
        module.myPrint("主机名 $hostname === "..ngx.var.hostname);
        module.myPrint("客户端地址 $remote_addr === "..ngx.var.remote_addr);
        module.myPrint("客户端端口号 $remote_port === "..ngx.var.remote_port);
        module.myPrint("服务器端地址 $server_addr === "..ngx.var.server_addr);
        module.myPrint("服务器名 $server_name === "..ngx.var.server_name);
        module.myPrint("服务器端口 $server_port === "..ngx.var.server_port);
        module.myPrint("服务器的HTTP版本 $server_protocol === "..ngx.var.server_protocol);
        module.myTitlePrint("end to print nginx value...")
end

return module