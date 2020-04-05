# karuna-app

## Local Development

Download [GnuPG](https://gnupg.org/)
```
brew install gnupg
```
Decrypt google-services.json.gpg with the below commands

```
export GOOGLE_SERVICES_JSON_PASSPHRASE=<passphrase>

./.github/scripts/decrypt_google_services_json.sh  
```


