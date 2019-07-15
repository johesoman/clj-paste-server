# clj-paste-server

A simple Clojure web app that lets you
* Write your data by POSTing it as JSON to /store/path.
* Read that same data (and possibly more) by GETing /store/path.

## Usage

1. Run the project using lein.
```$ lein run```

2. By default, you can find the server at `https://localhost:3443`.

### Example

1. First, we POST some data
```
$ curl --insecure \
    -X POST https://localhost:3443/store/mypath \
    -H "Content-Type: application/json" \
    -d '{"data": "mydata"}'
```

2. Then, we GET the data
```
$ curl --insecure \
    -X GET https://localhost:3443/store/mypath
```

3. The body of the response looks something like this
```
[{"data":"mydata",
    "time":"2019-07-15T11:13:49.961410Z"}]
```

## License

Copyright © 2019 Joel Söderman

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
