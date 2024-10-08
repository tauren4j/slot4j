The first idea was to create at least 3 microservices:
- **Bridge Service**: Acts as a gateway for client requests, handling authentication, authorization, and session management.
- **Game Core Service**: Defines common interfaces and game mechanics that can be extended for different game types.
- **Slot Game Service**: Implements the specific mechanics of a slot machine game, including the symbol set, paytable, and game logic.
 
So the flow ideally should be:
The user chooses a game from the list.
Bridge service checks if the JWT is correct and not expired, generates a session, and stores it in the DB (preferably Redis)
The Websocket is opened based on JWT on the HTTP-handshake stage
Every spin request and response is a part of WebSocket request-response messaging, and every spin has its ID and is stored in the DB linked to the current session (so we will be able to recover the game state if smth goes wrong)
Balance is updated according to the game result (including bet amount) before each spin balance is checked to make sure the amount is enough for placing the next bet
On the balance update step, we can check if the spine was already paid to avoid duplicate request vulnerability, we use the secret key for a particular user and send it in HTTP headers to verify the request

Further steps to improve the application:
1) implement a simulation tool to be able to check the RTP properly
2) implement logging of every step of the flow
3) implement metrics and alerts for suspicious activities/spikes of errors
