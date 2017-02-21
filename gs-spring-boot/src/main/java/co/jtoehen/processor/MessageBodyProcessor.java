package co.jtoehen.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.converter.stream.FileInputStreamCache;


public class MessageBodyProcessor implements Processor
{
    public void process(Exchange exchange) throws Exception
    {
        FileInputStreamCache in = (FileInputStreamCache)exchange.getIn().getBody();

        StringBuilder builder = new StringBuilder();
        int ch;
        while((ch = in.read()) != -1){
            builder.append((char)ch);
        }

        in.close();
        in = null;

        String body = builder.toString();

        System.out.println(body);
        exchange.getIn().setBody(body);
    }
} 