package parser;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * Created by Mordr on 10.02.2017.
 * Запускает в отдельном потоке парсер текста
 */
public class ParserRunnable implements Runnable{
    private final String resource;
    private final Parser parser;
    private static final Logger logger = Logger.getLogger(ParserRunnable.class);
    static  {
        DOMConfigurator.configure("src/resources/log4j.xml");
    }

    public ParserRunnable(String resource, Parser parser) {
        this.resource = resource;
        this.parser = parser;
    }

    @Override
    public void run() {
        logger.trace("Thread started for " + resource);
        parser.parseResource(resource);
        logger.trace("End of thread for " + resource);
    }
}
