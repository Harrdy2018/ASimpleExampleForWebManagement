#!/usr/bin/lua
local stringModule = require("common.print_ngx_value")

module = {}
module.name = "print_header"

function module.print_req_header()
    stringModule.myTitlePrint("start to print request headers...")
    local h = ngx.req.get_headers()
    for k,v in pairs(h) do
        stringModule.myPrint(k.."="..v)
    end
    stringModule.myTitlePrint("end to print request headers...")
end

function module.print_resp_header()
    stringModule.myTitlePrint("start to print response headers...")
    local h = ngx.resp.get_headers()
    for k,v in pairs(h) do
        stringModule.myPrint(k.."="..v)
    end
    stringModule.myTitlePrint("end to print response headers...")
end

return module