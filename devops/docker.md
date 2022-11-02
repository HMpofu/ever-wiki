### Docker Cheat Sheet
Break down of the docker run command:

Example:  `docker run --rm -d -p 8080:80 --name web nginx`

And you may open the web service in your browser at `localhost:8080` to see it running.

To stop the container with SIGTERM `docker stop web`

To stop the container with SIGKILL`docker kill web`