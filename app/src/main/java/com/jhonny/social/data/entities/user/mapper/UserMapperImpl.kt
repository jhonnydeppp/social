package com.jhonny.social.data.entities.user.mapper


import com.jhonny.social.data.entities.user.entities.UserResponse
import com.jhonny.social.domain.entities.Dob
import com.jhonny.social.domain.entities.DomainUser
import com.jhonny.social.domain.entities.DomainUserItem
import com.jhonny.social.domain.entities.Location
import com.jhonny.social.domain.entities.Picture
import com.jhonny.social.domain.entities.Street
import com.jhonny.social.presenter.entities.Name
import javax.inject.Inject

class UserMapperImpl @Inject constructor() : UserMapper {

    override fun responseToDomain(info: UserResponse?): DomainUser {
        return DomainUser(
            list =
            info?.results?.map {
                DomainUserItem(
                    nat = it?.nat,
                    gender = it?.gender,
                    phone = it?.phone,
                    dob = Dob(
                        date = it?.dob?.date,
                        age = it?.dob?.age
                    ),
                    picture = Picture(
                        thumbnail = it?.picture?.thumbnail,
                        large = it?.picture?.large,
                        medium = it?.picture?.medium
                    ),
                    name = Name(
                        last = it?.name?.last,
                        first = it?.name?.first,
                        title = it?.name?.title
                    ),
                    location = Location(
                        country = it?.location?.country,
                        city = it?.location?.city,
                        street = Street(
                            number = it?.location?.street?.number,
                            name = it?.location?.street?.name
                        ),
                        postcode = it?.location?.postcode,
                    ),
                    cell = it?.cell,
                    email = it?.email
                )
            } ?: emptyList()
        )
    }

}
