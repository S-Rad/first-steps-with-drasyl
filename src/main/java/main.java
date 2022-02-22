import org.drasyl.node.DrasylConfig;
import org.drasyl.node.DrasylException;
import org.drasyl.node.DrasylNode;
import org.drasyl.node.event.Event;
import org.drasyl.node.event.MessageEvent;
import org.drasyl.node.event.NodeOnlineEvent;

import java.nio.file.Path;
import java.util.concurrent.CompletionStage;


public class main {

    public static void main(String[] args) {
        DrasylConfig config = DrasylConfig.newBuilder().identityPath(Path.of("second.identity")).build();

        DrasylNode node;
        try {
            node = new DrasylNode(config) {
                @Override public void onEvent(Event event) {
                    // handle incoming events (messages) here
                    System.out.println("Event received: " + event);
                    if (event instanceof NodeOnlineEvent) {
                        //String recipient = "1b5860924e8369e5e4cadd381131795a70bd1c6bd02a5508473068c1da40fb45";
                        //String recipient = "566416c1474c49625ba4f46abaea64aa08a0e708cd8e0a48ccfe626a0b3c3fed";
                        String recipient = "759b7f5d9d77af678d5f93677919afe49403c7178620d9957f68e9f9882e8d0e";
                        String payload = "Hello there";
                        CompletionStage<Void> send = this.send(recipient, payload);
//						send.toCompletableFuture().whenComplete((a, e) -> {
//				            if (e == null) {
//				            	System.out.println("Message send");
//				            }
//				        });
                    };
                    if(event instanceof MessageEvent){
                        MessageEvent message = (MessageEvent)event;
                        System.out.println("Message received from "+message.getSender()+" with payload " + new String((String) message.getPayload()));
                    }
                }
            };
            node.start(); // wait till NodeOnlineEvent has been received
            // send message to another node

            // shutdown node
            //node.shutdown();
        } catch (DrasylException e) {
            e.printStackTrace();
        }
    }
}
