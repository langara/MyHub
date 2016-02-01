package pl.mareklangiewicz.myhub.io

import com.noveogroup.android.log.Logger
import com.noveogroup.android.log.MyHandler
import com.noveogroup.android.log.MyLogger
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import pl.mareklangiewicz.myutils.MyTextUtils.str
import retrofit.Call
import retrofit.Response
import rx.Observable

class GitHubTest {

    private val log = MyLogger("UT")

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MyHandler.sPrintLnLevel = Logger.Level.VERBOSE
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {

    }

    // Tests below are manual tests for user to launch by hand (one by one)
    // and to inspect results on console or to set breakpoints and analyze step by step.


    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserCall() {
        val service = GitHub.create()
        var call: Call<GitHub.User> = service.getUserCall("langara")
        var response: Response<GitHub.User> = call.execute()
        var body: GitHub.User = response.body()
        log.w(str(body)) // set breakpoint here to see properties
        call = service.getUserCall("JakeWharton")
        response = call.execute()
        body = response.body()
        log.w(str(body)) // set breakpoint here to see properties
    }


    // WARNING: You need base64 encoded user name and password to run this test.
    // you can calculate it easily in python:
    // import base64
    // base64.b64encode("someuser:somepassword")
    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserAuthCall() {
        val service = GitHub.create()
        val call = service.getUserAuthCall("Basic some_bad_base64_pass")
        val response = call.execute()
        val body = response.body()
        log.w(str(body)) // set breakpoint here to see properties
    }

    // WARNING: see test above, and use correct OTP code as a second parameter
    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserTFACall() {
        val service = GitHub.create()
        val call = service.getUserTFACall("Basic some_bad_base64_pass", "421164")
        val response = call.execute()
        val body = response.body()
        log.w(str(body)) // set breakpoint here to see properties
    }

    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserReposCall() {
        val service = GitHub.create()
        var call: Call<List<GitHub.Repository>> = service.getUserReposCall("langara")
        var response: Response<List<GitHub.Repository>> = call.execute()
        var body: List<GitHub.Repository> = response.body()
        log.w(str(body)) // set breakpoint here to see properties
        call = service.getUserReposCall("JakeWharton")
        response = call.execute()
        body = response.body()
        log.w(str(body)) // set breakpoint here to see properties
    }


    // WARNING: You need base64 encoded user name and password to run this test.
    // you can calculate it easily in python:
    // import base64
    // base64.b64encode("someuser:somepassword")
    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserReposAuthCall() {
        val service = GitHub.create()
        val call = service.getUserReposAuthCall("Basic some_bad_base64")
        val response = call.execute()
        val body = response.body()
        log.w(str(body)) // set breakpoint here to see properties
    }


    // WARNING: see test above, and use correct OTP code as a second parameter
    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserReposTFACall() {
        val service = GitHub.create()
        val call = service.getUserReposTFACall("Basic some_bad_base64", "197187")
        val response = call.execute()
        val body = response.body()
        log.w(str(body)) // set breakpoint here to see properties
    }


    internal fun <T> subscribeAndLogAll(observable: Observable<T>) {
        val observer = object : rx.Observer<T> {
            override fun onCompleted() {
                log.i("completed.")
            }
            override fun onError(e: Throwable?) {
                log.e(e)
            }
            override fun onNext(t: T) {
                log.i(str(t))
            }
        }
        observable.subscribe(observer)
    }

    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserObservable() {

        val service = GitHub.create()
        var observable: Observable<GitHub.User> = service.getUserObservable("langara")
        subscribeAndLogAll(observable)

        observable = service.getUserObservable("JakeWharton")
        subscribeAndLogAll(observable)
    }

    // WARNING: You need base64 encoded user name and password to run this test.
    // you can calculate it easily in python:
    // import base64
    // base64.b64encode("someuser:somepassword")
    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserAuthObservable() {
        val service = GitHub.create()
        val observable = service.getUserAuthObservable("Basic some_bad_base64_pass")
        subscribeAndLogAll(observable)
    }

    // WARNING: see test above, and use correct OTP code as a second parameter
    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserTFAObservable() {
        val service = GitHub.create()
        val observable = service.getUserTFAObservable("Basic some_bad_base64_pass", "421164")
        subscribeAndLogAll(observable)
    }

    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserReposObservable() {
        val service = GitHub.create()
        var observable: Observable<List<GitHub.Repository>> = service.getUserReposObservable("langara")
        subscribeAndLogAll(observable)
        observable = service.getUserReposObservable("JakeWharton")
        subscribeAndLogAll(observable)
    }


    // WARNING: You need base64 encoded user name and password to run this test.
    // you can calculate it easily in python:
    // import base64
    // base64.b64encode("someuser:somepassword")
    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserReposAuthObservable() {
        val service = GitHub.create()
        val observable = service.getUserReposAuthObservable("Basic some_bad_base64")
        subscribeAndLogAll(observable)
    }


    // WARNING: see test above, and use correct OTP code as a second parameter
    @Ignore
    @Test
    @Throws(Exception::class)
    fun testGitHubGetUserReposTFAObservable() {
        val service = GitHub.create()
        val observable = service.getUserReposTFAObservable("Basic some_bad_base64", "197187")
        subscribeAndLogAll(observable)
    }


}