# rcmplayer

Remote Control for [MPlayer](http://www.mplayerhq.hu).

## How does it work?

It is a thin web wrapper around MPlayer so that its commands can be easily exposed via web API. It works seamless on Linux and Windows. In practice, you should be able to run this as server and easily control it from your mobile.

## Usage

Build the jar:
```
$ lein do clean, uberjar
```

Run:
```
$ java -jar <your-jar-file> --mplayer /path/to/mplayer --port 5000 -- -fs -sub /path/to/subtitle /path/to/video
```

Visit [http://localhost:5000](http://localhost:5000).

## License

Copyright © 2014 Jivago Alves

Distributed under the MIT License
