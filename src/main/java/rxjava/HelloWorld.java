package rxjava;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;

/**
 * 
 * @author 张大川
 * 第一个示例
 */
public class HelloWorld {
	public static void main(String[] args) {
		Flowable<String> flowable = Flowable.create(new FlowableOnSubscribe<String>() {

			@Override
			public void subscribe(FlowableEmitter<String> e) throws Exception {
				e.onNext("xxx");
				e.onNext("hello RxJava 2");
				e.onComplete();
			}
		}, BackpressureStrategy.BUFFER);
		Subscriber<String> subscriber = new Subscriber<String>() {
			@Override
			public void onSubscribe(Subscription s) {
				System.out.println("onSubscribe");
				s.request(Long.MAX_VALUE);
			}

			@Override
			public void onNext(String s) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(s);
			}

			@Override
			public void onError(Throwable t) {

			}

			@Override
			public void onComplete() {
				System.out.println("onCompxxx");
			}

		};

		flowable.subscribe(subscriber);
	}
}
