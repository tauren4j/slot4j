The first idea was to create at least 3 microservices:
1) Bridge service - for communicating between the client platform and our engine (generating session for the user, checking JWT for verifying requests, updating balance based on game result)
2) Game core service - base service for all games, providing interfaces to be implemented and extended in every game (using their own symbols, probabilities, game field size, etc)
3) The realization of a slot game - overriding game core values to specific for this game, game field size, probabilities, and symbols are hardcoded, but the other configurations like possible bets, and possible bet lines should be dynamically configured from the back office and be stored in the DB
 

**So the flow from my view is next:**
The user chooses a game from the list.
Bridge service checks if the JWT is correct and not expired, generates a session, and stores it in the DB (preferably Redis)
The Websocket is opened based on JWT on the HTTP-handshake stage
Every spin request and response is a part of WebSocket request-response messaging, and every spin has its ID and is stored in the DB linked to the current session (so we will be able to recover the game state if smth goes wrong)
Balance is updated according to the game result (including bet amount) before each spin balance is checked to make sure the amount is enough for placing the next bet
On the balance update step, we can check if the spine was already paid to avoid duplicate request vulnerability, we use the secret key for a particular user and send it in HTTP headers to verify the request
