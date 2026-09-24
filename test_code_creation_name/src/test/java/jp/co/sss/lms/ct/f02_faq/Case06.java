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
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

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
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		// test04で開いた別タブへ操作対象を切り替え
		List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
		webDriver.switchTo().window(tabs.get(tabs.size() - 1));

		// 1. よくある質問画面のカテゴリ検索欄にて「【研修関係】」リンクを押下
		webDriver.findElement(By.linkText("【研修関係】")).click();

		// 検索結果画面にて対象の質問が表示されていることを確認
		String pageText = webDriver.findElement(By.tagName("body")).getText();
		assertTrue(pageText.contains("検索結果"));
		assertTrue(pageText.contains("キャンセル料・途中退校について"));
		assertTrue(pageText.contains("研修の申し込みはどのようにすれば良いですか？"));
		
		// 2. エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		// 1. 検索結果画面にて「Q. キャンセル料・途中退校について」を押下
		// partialLinkText から XPath 指定に変更
		webDriver.findElement(By.xpath("//*[contains(text(),'キャンセル料・途中退校について')]")).click();

		// 【アサーション】Qの下に回答メッセージが表示されていることを確認
		String pageText = webDriver.findElement(By.tagName("body")).getText();
		assertTrue(
				pageText.contains("A. 受講者の退職や解雇等、やむを得ない事情による途中終了に関してなど、事情をお伺いした上で、協議という形を取らせて頂きます。 弊社営業担当までご相談下さい。"));

		// 2. エビデンス取得
		getEvidence(new Object() {
		});
	}

}
