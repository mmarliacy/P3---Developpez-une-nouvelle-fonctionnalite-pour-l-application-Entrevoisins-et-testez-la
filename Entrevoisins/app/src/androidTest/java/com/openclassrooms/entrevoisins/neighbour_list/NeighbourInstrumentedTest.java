
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static com.openclassrooms.entrevoisins.utils.SelectItemView.withIndex;
import static com.openclassrooms.entrevoisins.utils.SelectTabByPosition.selectTabAtPosition;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighbourInstrumentedTest {

    // This is fixed
    private static final int ITEMS_COUNT = 12;
    private static final int FAVORITES_ITEMS_COUNT = 0;
    NeighbourApiService mApiService;

    {
        mApiService = DI.getNewInstanceApiService();
    }

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        ListNeighbourActivity mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * Check if when we click on an item, ProfileActivity is correctly launched.
     */
    @Test
    public void profileActivityIsLaunched() {
        onView((withId(R.id.list_neighbours)))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.profile_activity_constraint_layout)).check(matches(isDisplayed()));
    }

    /**
     * Check if when we click on an item, ProfileActivity is correctly set up the TextView.
     */
    @Test
    public void nameHasBeenSetUp() {
        onView((withId(R.id.list_neighbours))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.profile_activity_constraint_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_activity_name)).check(matches(withText(mApiService.getNeighbours().get(0).getName())));

    }
    /**
     * When we delete an item, the item is no more shown
     * */
    @Test
    public void deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.list_neighbours), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * Check if favorite tab only contains favorites neighbours
     */
    @Test
    public void favoriteTabContainsOnly_favoritesNeighbours (){
        // Display the profile that we clicked on
        onView((withId(R.id.list_neighbours))).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.profile_activity_constraint_layout)).check(matches(isDisplayed()));
        onView(withId(R.id.profile_activity_name)).check(matches(withText(mApiService.getNeighbours().get(0).getName())));

        //Click on Favorite Button
        onView((withId(R.id.profile_activity_fab))).perform(click());

        //Click on back Button
        onView((withId(R.id.profile_activity_constraint_layout))).perform(pressBack());
        onView(withId(R.id.list_neighbours)).check(matches(isDisplayed()));

        //Swipe to Tab two
        onView(withId(R.id.tabs)).perform(selectTabAtPosition(1));

        //Now there's a new favorite contact in favorite recyclerview.
        onView(withId(R.id.list_fav_neighbours)).check(withItemCount(FAVORITES_ITEMS_COUNT + 1));

        //Click again in the neighbour profile to show it's the same
        onView((withId(R.id.list_fav_neighbours))).check(matches(isDisplayed()));
        onView(withIndex(withId(R.id.item_list_name), 0)).check(matches(withText(mApiService.getNeighbours().get(0).getName())));
        }
    }
