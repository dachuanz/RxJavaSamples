package rxjava;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
/**
 * 
 * 
 * @author 张大川
 *
 */
public class HelloWorld2 {
	public static void main(String[] args) {
		
		/**
		 * Flowable  实现 Publisher接口
		   
		 * Publisher 为发布者的含义
		 */
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

		Subscriber<String> subscriber2 = new Subscriber<String>() {

			@Override
			public void onSubscribe(Subscription arg0) {
				System.out.println("第二个订阅类");
				arg0.request(Long.MAX_VALUE);

			}

			@Override
			public void onNext(String arg0) {
				System.out.println("第二个订阅类" + arg0);
			}

			@Override
			public void onError(Throwable arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onComplete() {
				// TODO Auto-generated method stub

			}
		};

		flowable.subscribe(subscriber);// 一个发布者可以为多个订阅者订阅
		flowable.subscribe(subscriber2);
	}
}
