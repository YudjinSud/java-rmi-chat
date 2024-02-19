./run.sh

//launch rmiregistry on mandelbrot on port 6090
rmiregistry 6090 &

//launch the server
java -Djava.rmi.server.hostname=localhost  -cp classes src.HelloServer 6090

//launch the client
java -cp classes/ src.HelloClient localhost 6090

![diagram](diagram.png)