Scenario: Logowanie pomyslne

Given Mam webdrivera
When Zaloguje się na konto: <login> z hasłem: <password>
Then Zobaczę <message> na stronie głównej

Examples:
|login          |password  |message               |
|admin@admin.com|admin1    |Hello admin@admin.com!|
|mod@mod.com    |moderator1|Hello mod@mod.com!    |
|user@user.com  |user12    |Hello user@user.com!  |

Scenario: Logowanie nieudane

Given Mam webdrivera
When Logowanie na nieistniejace konto
Then Zostane na stronie logowania

Scenario: Bledne dodawanie autora

Given Zalogowany jako admin
When Dodaje autora z imieniem z malych liter
Then Zobacze informacje o blednym imieniu

Given Zalogowany jako admin
When Dodaje autora z nazwiskiem z malych liter
Then Zobacze informacje o blednym nazwisku

Given Zalogowany jako admin
When Dodaje autora z imieniem i nazwiskiem z malych liter
Then Zobacze informacje o problemach z walidacja

Scenario: Prawidlowe dodawanie autora

Given Zalogowany jako admin
When Dodaje autora z prawidlowymi danymi
Then Zobacze dodanego autora

Scenario: Wyszukiwanie na stronie autora

Given Zalogowany jako uzytkownik
When Wpisuje pusty napis do wyszukiwarki
Then Wyswietla wszystkie dane

Given Zalogowany jako uzytkownik
When Wpisuje Mickiewicz do wyszukiwarki
Then Otrzymam 2 wyniki

Given Zalogowany jako uzytkownik
When Wpisuje nieistniejaca fraze do wyszukiwarki
Then Otrzymam pusta tabele

Scenario: Dodawanie nieprawidłowych ksiazek

Given Zalogowany jako admin, przekierowany na strone ksiazek
When Dodam ksiazke: <title> : <pages> : <date> : <price> : <author>
Then Zobaczę błąd o tresci : <message>

Examples:
|title|pages|date|price|author|iderror|message|
|Jas|200|10101995|a|1|Price-error|The field Price must be a number.|
|Jas|10000|10101999|15|1|Length-error|The field Number of pages must be between 1 and 8000.|


