# metadata-server-mock-api-test

Bugs:
1. Properties with data should in "value" contain case insensitive word "happy" or "amazing" depending on endpoint name
 - this fails for 'decimals', 'logo' and 'description' > happy
 - this fails for 'decimals', 'ticker', 'logo' and 'description' > amazing
2. Single value properties with added random string to path return 200 with "Requested property '_name_' not found", should return 404
3. Query always returns empty list on success, it should most probably return all subjects/properties or the ones added by this query
4. Query does not support adding properties only which seems inconsistent
5. Metadata with wrong method (ex. POST) returns 404, should return 405
6. Query with empty or malformed body returns 500, should return 400