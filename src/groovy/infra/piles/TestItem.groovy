package infra.piles

/**
 * @author alari
 * @since 2/28/13 12:05 AM
 */
class TestItem implements PiledItem {
    String id

    Set<TestPile> piles = []
    Set<TestPile> hidden = []
}
