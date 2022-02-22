import org.drasyl.node.DrasylNode;

public class main{

    public static void main(String[] args){
        DrasylNode node;

        {
            try{
                node = new DrasylNode() {
                    @Override public void onEvent(Event event) {
                        // handle incoming events (messages) here
                        System.out.println("Event received: " + event);
                    }
                };
            }
        }

        node.start(); // wait till NodeOnlineEvent has been received

        String recipient = "0229041b273dd5ee1c2bef2d77ae17dbd00d2f0a2e939e22d42ef1c4bf05147ea9";
        String payload = "Hello World";
        System.out.println("aaa");
        CompletationStage<Void> send = node.send(recipient, payload);
        send.toCompletableFuture().join();
        // shutdown node
        node.shutdown();

    }

}