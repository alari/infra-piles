package infra.piles

/**
 * @author alari
 * @since 2/28/13 12:04 AM
 */
class TestPile implements SortablePile {
    String id
    String title

    List<TestItem> plainItems = []
    List<TestItem> sortedItems = []
}
