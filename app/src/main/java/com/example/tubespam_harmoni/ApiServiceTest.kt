package com.example.tubespam_harmoni

@RunWith(AndroidJUnit4::class)
class ApiServiceTest {
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `test getPopularMusic API call`() {
        val response = MockResponse()
            .setResponseCode(200)
            .setBody("{\"items\": []}")

        mockWebServer.enqueue(response)

        val baseUrl = mockWebServer.url("/").toString()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(SpotifyService::class.java)
        val call = service.getPopularMusic()
        val result = call.execute()

        assert(result.isSuccessful)
        assert(result.body()?.items?.isEmpty() == true)
    }
}
