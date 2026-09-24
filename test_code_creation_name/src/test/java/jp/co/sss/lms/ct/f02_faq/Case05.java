package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

/**
 * 結合テスト よくある質問機能
 * ケース05
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

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
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
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

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// 1. 上部メニューの「機能」をクリックしてドロップダウンを表示
		webDriver.findElement(By.linkText("機能")).click();

		// 2. ドロップダウン内の「ヘルプ」をクリック
		webDriver.findElement(By.linkText("ヘルプ")).click();

		// ヘルプ画面に遷移していることを確認
		String pageText = webDriver.findElement(By.tagName("body")).getText();
		assertTrue(pageText.contains("ヘルプ"));

		// 3. エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// 1. ヘルプ画面上の「よくある質問」リンクをクリック
		webDriver.findElement(By.linkText("よくある質問")).click();

		// 2. 別タブに切り替え
		List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
		webDriver.switchTo().window(tabs.get(tabs.size() - 1));

		// 別タブでよくある質問画面が開いていることを確認
		String pageText = webDriver.findElement(By.tagName("body")).getText();
		assertTrue(pageText.contains("よくある質問"));

		// 3. エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {

		//検索キーワード「キャンセル」を入力
		webDriver.findElement(By.name("keyword")).sendKeys("キャンセル");

		// 検索ボタンを押下
		webDriver.findElement(By.xpath("//button[contains(text(),'検索')] | //input[@value='検索']")).click();

		// 検索結果にキーワードが含まれることを検証
		String pageText = webDriver.findElement(By.tagName("body")).getText();
		assertTrue(pageText.contains("キャンセル"));

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {
		// 「クリア」ボタンを押下
		webDriver.findElement(By.xpath("//button[contains(text(),'クリア')] | //input[@value='クリア']")).click();

		// 入力欄の値（value）が空文字になっていることを検証
		String keywordValue = webDriver.findElement(By.name("keyword")).getAttribute("value");
		assertEquals("", keywordValue);

		// エビデンス取得
		getEvidence(new Object() {
		});
	}

}
