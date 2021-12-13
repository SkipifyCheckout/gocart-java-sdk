# GoCart Java Client Library

## Requirements

- Java 1.8 or later
- Merchant Id and Merchant Api Key. These should have been provided to you in onboarding.

### Maven Dependency

```xml
<dependency>
    <groupId>io.github.gocartpay</groupId>
    <artifactId>gocart-java-sdk</artifactId>
    <version>1.0.2</version>
</dependency>
```

### Gradle Dependency
```
implementation 'io.github.gocartpay:gocart-java-sdk:1.0.2'
```

### If you would rather manually install the JAR
You can find the jar [here](https://github.com/GoCartPay/gocart-java-sdk/releases/tag/1.0.2)

## Documentation

Refer to [GoCart Documentation](https://docs.gocartpay.com/docs) for additional information that is not included in this readme.

### Configuration Required To Use The SDK
**You will need to create the following environment variables:**
- GOCART_MERCHANTID = {your_merchant_id}
- GOCART_APIKEY = {your_api_key}

### Authentication
The GoCart APi uses HMAC to authenticate most requests. The gocart-java-sdk will do HMAC authorization for you.
You just need to have your [properties set](#Configuration-Required-To-Use-The-Sdk).

## Examples of How To Use The SDK
For examples of how to use the sdk visit the repository [wiki](https://github.com/GoCartPay/gocart-java-sdk/wiki/GoCart-Java-SDK-Code-Samples).




