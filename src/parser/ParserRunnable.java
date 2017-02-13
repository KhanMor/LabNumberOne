package parser;

/**
 * Created by Mordr on 10.02.2017.
 * Потоки
 */
public class ParserRunnable implements Runnable{
    private final String resource;

    public ParserRunnable(String resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        System.out.println("Thread started for " + resource);
        Parser parser = new Parser();
        parser.parseResource(resource);
        System.out.println("End of thread for " + resource);
    }
}
