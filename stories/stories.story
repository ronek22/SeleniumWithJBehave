Scenario: Logowanie

Given Mam webdrivera
When Zaloguje się na konto: <login> z hasłem: <password>
Then Zobaczę <message> na stronie głównej

Examples:
|login          |password  |message               |
|admin@admin.com|admin1    |Hello admin@admin.com!|
|mod@mod.com    |moderator1|Hello mod@mod.com!    |
|user@user.com  |user12    |Hello user@user.com!  |

Scenario: Bledne dodawanie autora

Given Zalogowany jako admin
When Dodaje autora z imieniem z malych liter
Then Zobacze informacje o blednym imieniu

Given Zalogowany jako admin
When Dodaje autora z nazwiskiem z malych liter
Then Zobacze informacje o blednym nazwisku