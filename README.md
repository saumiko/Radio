# Radio
These Java Applications are my networking lab project. The task was to build a local network radio that can broadcast voice from a server PC to multiple clients. Here the server program creates a TCP based VOIP streamer and the client program gets connected to it. There is a new port opened in the server every time a client gets connected. This will be able to handle some number of clients at a time.

# How to build & run
The build and running process for this project is described below:
- Open `RadioLan_Server` & `RadioLan_Client` projects in your Netbeans IDE.
- Run the `RadioLan_Server` project. Press the `►` button. It will create the TCP socket.
- Run the `RadioLan_Client` project. Enter the shown IP address & Port number displayed in the broadcaster program.
- You're good to go! :smiley:
- To stop the streaming just press the `■` button in the Server program and close the clients afterwords.

# Issues
I don't have any further plans to continue this project in future as it is ready for submission. :stuck_out_tongue_winking_eye: If you wanna make some changes and resolve the existing issues I would appriciate it. :wink:

- `.jar` file making fails.
- Some hazardous exceptions are not handled here. :stuck_out_tongue:

© :bangladesh: Asif Mohaimen, 2016