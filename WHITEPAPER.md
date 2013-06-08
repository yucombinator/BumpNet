BumpNet White Paper
=======
What should the app do?
=====
On successful connection, the app transfer all current messages contained within the
database to the receipient phone. Then it should merge its database of messages with the database it has received from
the other phone.

Means of communication include NFC("bumping"), Bluetooth (whether this is do-able remains to be seen...), and of course Wifi
(Wifi-Direct or Ad-hoc). When a phone is connected to the Internet or to a wifi network, it could start finding peers to share its database with
(P2P), this ensures faster spread of information to nearby nodes.

Messages should be stored either as plaintext (for public messages), or as cryptext (GPG-style?) that is only reabable to 
intended receipient.
