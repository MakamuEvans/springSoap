package com.last.last.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class CDATAAdaptor extends XmlAdapter<String, String> {
    @Override
    public String unmarshal(String s) throws Exception {
        return s;
    }

    @Override
    public String marshal(String s) throws Exception {
        return "<![CDATA[" + s + "]]>";
    }
}
