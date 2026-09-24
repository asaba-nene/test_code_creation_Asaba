package jp.co.sss.lms.ct.f01_login1;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.Assert.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

/**
 * 結合テスト ログイン機能①
 * ケース03
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース03 受講生 ログイン 正常系")
public class Case03 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {

		// 1. トップページ（URL）にアクセス
		goTo("http://localhost:8080/lms/");

		// LMSログイン画面へ遷移したことを確認
		assertEquals("ログイン | LMS", webDriver.getTitle());

		// 2. エビデンス（スクリーンショット）取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みユーザーでログイン")
	void test02() {
		// 1. ログインID「StudentAA02」を入力
		webDriver.findElement(By.name("loginId")).sendKeys("StudentAA02");

		// 2. パスワード「N0213s0913」を入力
		webDriver.findElement(By.name("password")).sendKeys("N0213s0913");

		// 3. ログインボタンを押下
		webDriver.findElement(By.xpath("//input[@type='submit']")).click();

		// 「コース詳細」と「DEMOコース」が表示されているか確認
		String pageText = webDriver.findElement(By.tagName("body")).getText();
		assertTrue(pageText.contains("コース詳細"));
		assertTrue(pageText.contains("DEMOコース"));

		// 4. エビデンス取得
		getEvidence(new Object() {
		});
	}

}
