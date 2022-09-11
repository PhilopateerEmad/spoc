package com.raphel.spoc.data.mappers.domainAndRemoteMappers
import com.raphel.spoc.data.remote.models.LoginRemoteModel
import com.raphel.spoc.domain.mapper.IMapper
import com.raphel.spoc.domain.model.LoginDomainModel



class LoginDomainModelToLoginRemoteModelMapper : IMapper<LoginDomainModel, LoginRemoteModel> {
    override fun map(input: LoginDomainModel): LoginRemoteModel {
        return LoginRemoteModel(
            input.email,
            input.password,
        )
    }
}