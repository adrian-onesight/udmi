[**UDMI**](../../) / [**Docs**](../) / [**Specs**](./) / [Proxy](#)

# UDMI Proxy

The _UDMI Proxy_ is a cloud-based capability to proxy UDMI/MQTT exchanges through an
intermediary proxy cloud project. This allows sharing of data among different cloud
administrative domains. For example, given the two requirements:

* All data must be ingested through a project owned by _Organization X_.
* Application level software must run in a cloud project owned by _Organization Y_.

Then the UDMI Proxy will allow resolving this by proxying data through a Proxy project
owned by _Organization X_ through to the project owned by _Organization Y_.

From a device perspective, it's 'transparent' in the sense
that there's no functional difference between communicating with the proxy project
and the original target project. The switch from one project to another is based on
updating the device's configuration (Cloud IoT Core endpoint).

Configuration of the Cloud IoT Core registry in both projects is accomplished through
the site's `site_model` and the `registrar` tool. The same model is run against both
projects, executed by an appropriately authorized agent with appropriate access rights.

Before using the project, it's generally best practice to test a few device connections
direct to the target project, to make sure it's known-working before switching the
devices over to a proxied connection. This is considered the _dev_ setup below, in
comparison to the _proxy_ setup, which is required for a complete install.

Internally, the Proxy project needs special setup and operation, but that is beyond
the scope of this document, and doesn't directly affect the device or target project
setups.

## Sequence Diagram

* _Devices_ are the UDMI/MQTT clients talking to GCP endpoints.
* _Model_ is the building site model.
* _Proxy_ is the intermediate GCP project.
* _Target_ is the end-use GCP project.
* _Dev Setup_ means the devices are configured to talk directly to the Target project.
* _Proxy Setup_ means devices are (re)configured to talk directly to the Proxy project.
* _registrar_ is the UDMI `registrar` tool, run by an authorized (per target project) agent.
* _connect_ is the process of a client connecting to it's configured target.

```
     +---------+ +-------+       +-------+     +---------+
     | Devices | | Model |       | Proxy |     | Target  |
     +---------+ +-------+       +-------+     +---------+
--------\ |          |               |              |
| Dev   |-|          |               |              |
| Setup | |          |               |              |
|-------| |          |               |              |
          |          | registrar     |              |
          |          |----------------------------->|
          |          |               |              |
          | connect  |               |              |
          |---------------------------------------->|
--------\ |          |               |              |
| Proxy |-|          |               |              |
| Setup | |          |               |              |
|-------| |          |               |              |
          |          | registrar     |              |
          |          |-------------->|              |
          |          |               |              |
          | connect  |               |              |
          |------------------------->|              |
          |          |               |              |
          |          |               | connect      |
          |          |               |------------->|
          |          |               |              |
```

Generated by https://textart.io/sequence
```
object Devices Model Proxy Target
note left of Devices: Dev\nSetup
Model->Target: registrar
Devices->Target: connect
note left of Devices: Proxy\nSetup
Model->Proxy: registrar
Devices->Proxy: connect
Proxy->Target: connect
```