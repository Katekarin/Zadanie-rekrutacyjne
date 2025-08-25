@SpringBootTest(webEnvironment = RANDOM_PORT)
class RepositoryIntegrationTest {

    @Autowired TestRestTemplate restTemplate;

    @Test
    void shouldReturnRepositoriesForExistingUser() {
        // given
        String username = "octocat";

        // when
        ResponseEntity<RepositoryResponse[]> response =
            restTemplate.getForEntity("/repositories/" + username, RepositoryResponse[].class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotEmpty();
        // sprawdź, że zawiera nazwy repo i branche z commit sha
    }
}
