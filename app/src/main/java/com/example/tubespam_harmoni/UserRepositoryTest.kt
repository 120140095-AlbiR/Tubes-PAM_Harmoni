package com.example.tubespam_harmoni

import com.google.firebase.auth.FirebaseAuth

@RunWith(MockitoJUnitRunner::class)
class UserRepositoryTest {
    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    private lateinit var userRepository: UserRepository

    @Before
    fun setUp() {
        userRepository = UserRepository(firebaseAuth)
    }

    @Test
    fun `test login success`() {
        val email = "test@example.com"
        val password = "password"
        val task = mock(Task::class.java)

        `when`(firebaseAuth.signInWithEmailAndPassword(email, password)).thenReturn(task)
        `when`(task.isSuccessful).thenReturn(true)

        val result = userRepository.login(email, password)

        assert(result.isSuccessful)
    }
}
